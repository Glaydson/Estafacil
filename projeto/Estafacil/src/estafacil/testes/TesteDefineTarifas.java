package estafacil.testes;

import estafacil.negocio.TipoTarifaVeiculo;

public class TesteDefineTarifas {

	public static void main(String[] args) {

		double tarifa1 = 5.0;
		TipoTarifaVeiculo.GRANDE.setTarifa(tarifa1);

		double tarifa2 = 4.0;
		TipoTarifaVeiculo.PEQUENO.setTarifa(tarifa2);

		double tarifa3 = 3.0;
		TipoTarifaVeiculo.MOTO.setTarifa(tarifa3);

		TipoTarifaVeiculo[] tipos = TipoTarifaVeiculo.values();
		for (TipoTarifaVeiculo t : tipos) {
			System.out.print(t.name() + " - ");
			System.out.println(t.getTarifa());
		}

	}

}
