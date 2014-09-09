package estafacil.negocio;

public class Veiculo {

	private int idVeiculo;
	private String placas;
	private String marca;
	private String modelo;
	private TipoTarifaVeiculo tipoVeiculo;

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getPlacas() {
		return placas;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public TipoTarifaVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoTarifaVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

}
