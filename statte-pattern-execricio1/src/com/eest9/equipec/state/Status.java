package com.eest9.equipec.state;

public interface Status {
	
	void solicitar();
	void aprovar();
	void recusar();
	void retornar(String motivo);

}
