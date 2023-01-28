package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	
	private String name;
	private String email;
	private String phone;
	private String password;
	
	// Não cria o metodo 'Set' pq não vamos trocar essa lista momento nenhum //
	// O atributo User está mapeado do outro lado, entidade Order, pelo atributo 'client' //
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