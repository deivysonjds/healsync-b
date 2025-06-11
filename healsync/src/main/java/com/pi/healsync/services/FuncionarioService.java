package com.pi.healsync.services;

import com.pi.healsync.DTO.funcionario.FuncionarioRequestDTO;
import com.pi.healsync.repositories.HospitalRepository;
import com.pi.healsync.models.Hospital;
import com.pi.healsync.models.Funcionario;
import com.pi.healsync.repositories.FuncionarioRepository;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.ObjectNotCreated;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Funcionario findByEmail(String email) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);

        if (!funcionario.isPresent()) {
            throw new NoSuchException("Funcionario");
        }

        return funcionario.get();
    }

    @Transactional(readOnly = true)
    public Funcionario findById(UUID id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if (!funcionario.isPresent()) {
            throw new NoSuchException("Funcionario");
        }

        return funcionario.get();
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findAllByHospital(Hospital hospital) {
        return funcionarioRepository.findByHospital(hospital);
    }

    @Transactional
    public Funcionario insert(Funcionario funcionario, UUID hospitalId) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
        if (!hospitalOptional.isPresent()) {
            throw new NoSuchException("Hospital");
        }

        funcionario.setHospital(hospitalOptional.get());

        // Encodar a senha
        String senhaEncode = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncode);

        try {
            return funcionarioRepository.save(funcionario);
        } catch (Exception e) {
            throw new ObjectNotCreated(e);
        }
    }

    @Transactional
    public Funcionario update(Funcionario funcionario, FuncionarioRequestDTO dto) {
        // Atualize os atributos
        funcionario.setName(dto.getName());
        funcionario.setCpf(dto.getCpf());
        funcionario.setAge(dto.getAge());
        funcionario.setEndereco(dto.getEndereco());
        funcionario.setRg(dto.getRg());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTelefone(dto.getPhone());
        funcionario.setRole(dto.getRole());
        // Se desejar, possa atualizar a senha aqui se ela foi informada
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            // código para criptografar senha
            funcionario.setSenha(dto.getPassword());
        }
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new NoSuchException("Funcionario");
        }
        funcionarioRepository.deleteById(id);
    }

}
