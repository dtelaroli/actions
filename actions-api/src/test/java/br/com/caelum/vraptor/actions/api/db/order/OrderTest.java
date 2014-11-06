package br.com.caelum.vraptor.actions.api.db.order;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.order.Order.Direction;

public class OrderTest {

	@Test
	public void shouldSetAscOrder() {
		Order order = Order.asc("foo");
		
		assertThat(order.property(), equalTo("foo"));
		assertThat(order.direction(), equalTo(Direction.ASC));
	}
	
	@Test
	public void shouldSetDescOrder() {
		Order order = Order.desc("foo");
		
		assertThat(order.property(), equalTo("foo"));
		assertThat(order.direction(), equalTo(Direction.DESC));
	}
	
	@Test
	public void shouldReturnToString() {
		Order order = Order.desc("foo");
		
		assertThat(order.property(), equalTo("foo"));
		assertThat(order.direction(), equalTo(Direction.DESC));
		assertThat(order.toString(), equalTo("foo DESC"));
	}

}
