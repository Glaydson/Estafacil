package estafacil.negocio;

public enum TipoTarifaVeiculo {

	GRANDE, PEQUENO, MOTO;

	private int idTipoTarifaVeiculo;
	private double tarifa;

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public int getIdTipoTarifaVeiculo() {
		return idTipoTarifaVeiculo;
	}

	public void setIdTipoTarifaVeiculo(int idTipoTarifaVeiculo) {
		this.idTipoTarifaVeiculo = idTipoTarifaVeiculo;
	}

}
