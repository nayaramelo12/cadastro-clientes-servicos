package io.github.udemy.clientesapi.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BigDecimalConverterTest {
	
	@Test
	void converterSucesso() {
		BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();
		String valorAtual = "1.000,00";
		
		BigDecimal valorEsperado = bigDecimalConverter.converter(valorAtual);
		assertNotEquals(valorEsperado, valorAtual);
	}
	
	@Test
	void converterNulo() {
		BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();
		String valor = null;
		
		BigDecimal valorEsperado = bigDecimalConverter.converter(valor);
		assertNull(valorEsperado);
	}

}

