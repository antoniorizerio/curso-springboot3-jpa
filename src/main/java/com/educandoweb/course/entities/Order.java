package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Estratégia para geração da chave primaria: IDENTITY //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Classe Instant veio substituir a classe Date //
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	// No Banco de dados terei uma chava estrangeira chamada 'client_id' que vai conter o 
	// ID do usuário associado a esse pedido 
	@ManyToOne
	@JoinColumn(name="client_id", nullable = false)
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	// Como utilizamos a anotação: @MapsId, fizemos com que as duas entidades tivessem o mesmo ID.
	// Com isso precisamos da operação Cascade, se Order for excluido o pagamento tbm o será.
	// * A função do cascade é cascatear operações de persistência.
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	

	public Order() {
		
	}

	public Order(Long id, Instant moment, OrderStatusEnum orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x: getItems()) {
			sum += x.getSubTotal();
		}
		return sum;
	}
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatusEnum getOrderStatus() {
		return OrderStatusEnum.valueOf(orderStatus);
	}
	public void setOrderStatus(OrderStatusEnum orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems() {
		return items;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}