package trier.jovemdev.provaum.guilherme_monteiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteCriacaoDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.security.Permissoes;
import trier.jovemdev.provaum.guilherme_monteiro.enums.TipoComidaEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "restaurante")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 80, nullable = false)
	private String nome;

	@Column(length = 80, nullable = false, unique = true)
	private String email;

	private String password;
	
	@Column(length = 14, nullable = false, unique = true)
	private String cnpj;
	
	@Column(length = 3)
	private Integer estrelas;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_comida", nullable = false)
	private TipoComidaEnum tipoComida;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<ClienteEntity> clientes;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<FuncionarioEntity> funcionarios;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<MesaEntity> mesas;

	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Column
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurante_permissao",
			joinColumns = {@JoinColumn(name = "id_restaurante")},
			inverseJoinColumns = {@JoinColumn(name = "id_permissao")})
	private List<Permissoes> permissoes;

	public List<String> getRoles(){
		return permissoes.stream().map(Permissoes::getDescricao).toList();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public RestauranteEntity(RestauranteDto restauranteDto) {
		this.id = restauranteDto.getId();
		this.nome = restauranteDto.getNome();
		this.cnpj = restauranteDto.getCnpj();
		this.estrelas = restauranteDto.getEstrelas();
		this.tipoComida = restauranteDto.getTipoComida();
	}

	public RestauranteEntity(RestauranteCriacaoDto restauranteCriacaoDto) {
		this.id = restauranteCriacaoDto.getId();
		this.nome = restauranteCriacaoDto.getNome();
		this.email = restauranteCriacaoDto.getEmail();
		this.password = restauranteCriacaoDto.getPassword();
		this.cnpj = restauranteCriacaoDto.getCnpj();
		this.estrelas = restauranteCriacaoDto.getEstrelas();
		this.tipoComida = restauranteCriacaoDto.getTipoComida();
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}

	public void atualizaCampos(RestauranteDto restauranteDto) {
		this.nome = restauranteDto.getNome();
		this.cnpj = restauranteDto.getCnpj();
		this.estrelas = restauranteDto.getEstrelas();
		this.tipoComida = restauranteDto.getTipoComida();
	}
}
