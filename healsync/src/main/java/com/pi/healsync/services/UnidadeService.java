    package com.pi.healsync.services;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import com.pi.healsync.exceptions.NoSuchException;
    import com.pi.healsync.exceptions.ObjectNotCreated;
    import com.pi.healsync.models.Hospital;
    import com.pi.healsync.models.Unidade;
    import com.pi.healsync.repositories.HospitalRepository;
    import com.pi.healsync.repositories.UnidadeRepository;

    @Service
    public class UnidadeService {

        @Autowired
        private UnidadeRepository unidadeRepository;
        @Autowired 
        private HospitalRepository hospitalRepository;

        @Transactional
        public Unidade insert(Unidade unidade, UUID hospitalId){

            Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
            
            if (!hospitalOptional.isPresent()) {
                throw new NoSuchException("Hospital");
            }

            unidade.setHospital(hospitalOptional.get());

            try {
                unidadeRepository.save(unidade);
            } catch (Exception e) {
                throw new ObjectNotCreated(e);
            }

            return unidade;
        }

        @Transactional(readOnly = true)
        public List<Unidade> findAllByHospital(Hospital hospital){

            List<Unidade> unidades = new ArrayList<>(unidadeRepository.findByHospital(hospital));
            
            return unidades;
        }

        @Transactional
        public Unidade findById(UUID id){

            Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
            
            if (!unidadeOptional.isPresent()) {
                throw new NoSuchException("Unidade");
            }

            return unidadeOptional.get();
        }

        @Transactional
        public void deleteById(UUID id) {
            if (!unidadeRepository.existsById(id)) {
                throw new NoSuchException("Unidade");
            }
            unidadeRepository.deleteById(id);
        }

        @Transactional
        public Unidade update(Unidade unidade) {
            if (!unidadeRepository.existsById(unidade.getId())) {
                throw new NoSuchException("Unidade");
            }
            try {
                return unidadeRepository.save(unidade);
            } catch (Exception e) {
                throw new ObjectNotCreated(e);
            }
        }
    }
