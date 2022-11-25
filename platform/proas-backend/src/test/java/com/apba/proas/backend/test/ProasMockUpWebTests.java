package com.apba.proas.backend.test;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.Variable;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProasMockUpWebTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc; // sirve para no cargar la web completa, hacer los tests más rapidos

	Logger logger = LoggerFactory.getLogger(ProasBackendApplicationTests.class);
	AOI aoi;
	static int otherID = 9;

	public ProasMockUpWebTests() {
		super();
	}

	@PostConstruct
	void init() {
		if (aoi == null) {
			aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
			AoiBuilder.buildAOI(otherID, Variable.Type.WIND.toString());
			log(getClass().getCanonicalName() + ": aoi creadas");
		}
	}

	private String getUriSinHttp(String URI) throws Exception {
		String s = this.mockMvc.perform(get(URI)).andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		return s;
	}

	@Test
	public void mockMvcAllTest() throws Exception {
		String s;
		s = getUriSinHttp("/proas-backend/test");
		assert s != null && s.contains("version");

		s = getUriSinHttp("/proas-backend/version");
		assert s != null && s.contains("version");

		s = getUriSinHttp("/proas-backend/config");
		assert s != null && s.contains("config");

		s = getUriSinHttp("/proas-backend/vessel");
		assert s != null && s.contains("Vessel");

		s = getUriSinHttp("/proas-backend/aois");
		assert s != null && s.contains("aoi");

		s = getUriSinHttp("/proas-backend/security/" + otherID);
		assert s != null && s.contains("aoi");

	}

	private void log(String s) {
		System.out.println("############# " + s + " ############# ");
	}

}
