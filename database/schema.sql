DROP TABLE scbs_queixa;
CREATE TABLE scbs_queixa
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

DROP TABLE scbs_funcionario;
CREATE TABLE scbs_funcionario
 (
	LOGIN			Text (100), 
	NOME			Text (100), 
	SENHA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_endereco;
CREATE TABLE scbs_endereco
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

DROP TABLE scbs_queixaalimentar;
CREATE TABLE scbs_queixaalimentar
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

DROP TABLE scbs_unidadesaude;
CREATE TABLE scbs_unidadesaude
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_unidadeespecialidade;
CREATE TABLE scbs_unidadeespecialidade
 (
	CODIGOUNIDADESAUDE			Text (100), 
	CODIGOESPECIALIDADE			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_especialidade;
CREATE TABLE scbs_especialidade
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_tipodoenca;
CREATE TABLE scbs_tipodoenca
 (
	CODIGO			Text (100), 
	nome			Text (100), 
	DESCRICAO			Text (100), 
	manifestacao			Text (100), 
	duracao			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_tipodoencasintoma;
CREATE TABLE scbs_tipodoencasintoma
 (
	CODIGOTIPODOENCA			Text (100), 
	CODIGOSINTOMA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_sintoma;
CREATE TABLE scbs_sintoma
 (
	CODIGO			Text (100), 
	DESCRICAO			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_queixaanimal;
CREATE TABLE scbs_queixaanimal
 (
	CODIGO			Text (100), 
	QTDEANIMAIS			Text (100), 
	DATAINCOMODO			Text (100), 
	ANIMAL			Text (100), 
	ENDERECOLOCALOCORRENCIA			Text (100)
);
-- CREATE ANY INDEXES ...

DROP TABLE scbs_queixadiversa;
CREATE TABLE scbs_queixadiversa
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
