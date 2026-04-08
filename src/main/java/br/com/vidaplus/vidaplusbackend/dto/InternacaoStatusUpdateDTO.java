package br.com.vidaplus.vidaplusbackend.dto;
public class InternacaoStatusUpdateDTO {

    private String status; // ADMITIDA | EM_OBSERVACAO | INTERNADA

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
