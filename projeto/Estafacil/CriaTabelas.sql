CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(10) NOT NULL,
  `senha` varchar(200) NOT NULL,
  `tipousuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `tipotarifaveiculo` (
  `idtipotarifaveiculo` int(11) NOT NULL AUTO_INCREMENT,
  `nometipo` varchar(10) NOT NULL,
  `tarifa` double NOT NULL,
  PRIMARY KEY (`idtipotarifaveiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `tipomovimentacao` (
  `idtipoMovimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `nometipo` varchar(10) NOT NULL,
  PRIMARY KEY (`idtipoMovimentacao`),
  UNIQUE KEY `nometipo_UNIQUE` (`nometipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `veiculo` (
  `idveiculo` int(11) NOT NULL AUTO_INCREMENT,
  `placas` varchar(7) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `idtipoveiculo` int(11) NOT NULL,
  PRIMARY KEY (`idveiculo`),
  UNIQUE KEY `idveiculos_UNIQUE` (`idveiculo`),
  KEY `tipoveiculo_idx` (`idtipoveiculo`),
  CONSTRAINT `tipoveiculo` FOREIGN KEY (`idtipoveiculo`) REFERENCES `tipotarifaveiculo` (`idtipotarifaveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `movimentacao` (
  `idmovimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `hora` varchar(5) NOT NULL,
  `tipomovimentacao` int(11) NOT NULL,
  `veiculo` int(11) NOT NULL,
  PRIMARY KEY (`idmovimentacao`),
  KEY `tipomovimentacao_idx` (`tipomovimentacao`),
  KEY `veiculo_idx` (`veiculo`),
  CONSTRAINT `tipomovimentacao` FOREIGN KEY (`tipomovimentacao`) REFERENCES `tipomovimentacao` (`idtipoMovimentacao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `veiculo` FOREIGN KEY (`veiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `pagamento` (
  `idpagamento` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `valor` double NOT NULL,
  `movimentacao` int(11) NOT NULL,
  PRIMARY KEY (`idpagamento`),
  UNIQUE KEY `idpagamentos_UNIQUE` (`idpagamento`),
  KEY `pagmovimentacao_idx` (`movimentacao`),
  CONSTRAINT `movimentacao` FOREIGN KEY (`movimentacao`) REFERENCES `movimentacao` (`idmovimentacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

