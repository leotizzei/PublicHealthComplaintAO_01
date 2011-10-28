-------------------------------------------------------------
-- MDB Tools - A library for reading MS Access database files
-- Copyright (C) 2000-2004 Brian Bruns
-- Files in libmdb are licensed under LGPL and the utilities under
-- the GPL, see COPYING.LIB and COPYING files respectively.
-- Check out http://mdbtools.sourceforge.net
-------------------------------------------------------------

DROP TABLE SCBS_queixa;
CREATE TABLE SCBS_queixa
 (
	CODIGO			Text (100), 
	TIPOQUEIXA			Text (100), 
	SOLICITANTE			Text (100), 
	DESCRICAO			Text (100), 
	OBSERVACAO			Text (100), 
	EMAIL			Text (100), 
	FUNCIONARIO			Text (100), 
	SITUACAO			Text (100), 
	dataPARECER			Text (100), 
	dataQUEIXA			Text (100), 
	ENDERECOSOLICITANTE			Text (100), 
	ts			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_funcionario;
CREATE TABLE SCBS_funcionario
 (
	LOGIN			Text (100), 
	NOME			Text (100), 
	SENHA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_endereco;
CREATE TABLE SCBS_endereco
 (
	codigo			Text (100), 
	rua			Text (100), 
	Complemento			Text (100), 
	Cep			Text (100), 
	Uf			Text (100), 
	Fone			Text (100), 
	Cidade			Text (100), 
	Bairro			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_queixaalimentar;
CREATE TABLE SCBS_queixaalimentar
 (
	CODIGO			Text (100), 
	QTDECOMENSAIS			Text (100), 
	QTDEDOENTES			Text (100), 
	QTDEINTERNACOES			Text (100), 
	QTDEOBITOS			Text (100), 
	LOCALATENDIMENTO			Text (100), 
	REFEICAOSUSPEITA			Text (100), 
	ENDERECODOENTE			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_UNIDADESAUDE;
CREATE TABLE SCBS_UNIDADESAUDE
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_unidadeespecialidade;
CREATE TABLE SCBS_unidadeespecialidade
 (
	CODIGOUNIDADESAUDE			Text (100), 
	CODIGOESPECIALIDADE			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_ESPECIALIDADE;
CREATE TABLE SCBS_ESPECIALIDADE
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_TIPODOENCA;
CREATE TABLE SCBS_TIPODOENCA
 (
	CODIGO			Text (100), 
	nome			Text (100), 
	DESCRICAO			Text (100), 
	manifestacao			Text (100), 
	duracao			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_tipodoencasintoma;
CREATE TABLE SCBS_tipodoencasintoma
 (
	CODIGOTIPODOENCA			Text (100), 
	CODIGOSINTOMA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_sintoma;
CREATE TABLE SCBS_sintoma
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_queixaanimal;
CREATE TABLE SCBS_queixaanimal
 (
	CODIGO			Text (100), 
	QTDEANIMAIS			Text (100), 
	DATAINCOMODO			Text (100), 
	ANIMAL			Text (100), 
	ENDERECOLOCALOCORRENCIA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE SCBS_queixadiversa;
CREATE TABLE SCBS_queixadiversa
 (
	CODIGO			Text (100), 
	IDADE			Text (100), 
	OCUPACAO			Text (100), 
	INSTRUCAO			Text (100), 
	ENDERECOOCORRENCIA			Text (100)
);
-- CREATE ANY INDEXES ...



-- CREATE ANY Relationships ...

-- relationships are not supported for access
