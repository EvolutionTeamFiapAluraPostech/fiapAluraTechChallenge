package br.com.fiapbook;


import br.com.fiapbook.shared.annotation.DatabaseTest;
import br.com.fiapbook.shared.annotation.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@IntegrationTest
@DatabaseTest
class NursingCalculatorApplicationTest {

	private final ApplicationContext applicationContext;

	@Autowired
	NursingCalculatorApplicationTest(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Test
	void contextLoads() {
		var fiapBookApplication = applicationContext.getBean(NursingCalculatorApplication.class);
		Assertions.assertThat(fiapBookApplication).isNotNull();
	}

}
