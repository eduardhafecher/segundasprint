package org.serratec.backend.gestao_competencias.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.serratec.backend.gestao_competencias.entity.Usuario;
import org.serratec.backend.gestao_competencias.entity.UsuarioPerfil;

import java.util.HashSet;
import java.util.Set;

public class UsuarioRequestDTO {

    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String senha;

    //Regex - para o cep
    private String cep;

    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();


    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }



    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
