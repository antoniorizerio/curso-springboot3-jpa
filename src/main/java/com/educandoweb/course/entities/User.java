package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Implementa a interface Serializable, indicando que meu objeto pode ser transformado
 * em cadeia de bytes. Para o objeto trafegar na rede, para que possa ser salvo em algum
 * arquivo.
 * 
 * @author Antonio Rizério JR
 *
 */

// Sempre fazer o import da especificação, no caso: jakarta.persistence //
@Entity
// Sempre renomear o nome da tabela no banco, para não ter conflito com palavras
// reservadas //
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Estratégia para geração da chave primaria: IDENTITY //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo nome não pode ser NULLO")
	private String name;
	@NotBlank
	@Email(message = "Campo e-mail incorreto" )
	private String email;
	@NotBlank(message = "Campo phone não pode ser NULLO")
	private String phone;
	@NotBlank(message = "Campo password não pode ser NULLO")
	private String password;
	
	// Não cria o metodo 'Set' pq não vamos trocar essa lista momento nenhum //
	// O atributo User está mapeado do outro lado, entidade Order, pelo atributo 'client' //
	// Foi criada uma associação dupla, na hora de chamar User ou Order, temos um Loop infinito //
	// Por conta disso colca-se um JsonIgnore em um dos lados //
	// Biblioteca Jackson responsável por tratar da serialização Java - Json //
	/**
	 * Quando vc tem uma associação Muitos para Um, se você carregar um objeto do lado do muitos, EX
	 * Order -> User, o User vem automaticamente. Agora do lado um para muitos, User -> Orders, O JPA não 
	 * carrega os objetos do lado do muitos por padrão -> lazy loading. No caso o Jackson que serializou
	 * o Json, ele solicita os pedidos do banco de dados -No caso a Classe Order-, 
	 * que por padrão não viriam, JPA por padrão não traz coleções de dados, Lazy Loading. O Jackson
	 * opera no final do ciclo de vida, chamando o banco novamente.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	

	// obrigatório ter um construtor vazio //
	public User() { 
		
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
	
	// getters and setters //
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
}