var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Alerta = require('../models').Alerta;

function dateToEN(date)
{	
	return date.split('/').reverse().join('-');
}

/* Recuperar os estabelecimentos de determinado usuario */
router.get('/recuperar/:idEstabelecimento', function(req, res, next) {
	
	var idEstabelecimento = req.params.idEstabelecimento;

	console.log(`Recuperando os alertas do estabelecimento do id ${idEstabelecimento}`);
	
	const instrucaoSql = `select distinct a.idAlerta,a.alerta,a.dataAlerta,a.fkMaquinaAlerta from alerta as a, 
						  kprMaquina as m, kprEstabelecimento as e where a.fkEstabelecimento = ${idEstabelecimento};`;
	sequelize.query(instrucaoSql, {
		model: Alerta,
		mapToModel: true
	  })
	  .then(resultado => {
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

/* Recuperar os estabelecimentos de determinado usuario para gerar um relatório*/
router.post('/relatorio/:idEstabelecimento', function(req, res, next) {
	
	var idEstabelecimento = req.params.idEstabelecimento;
	var dataInicial = req.body.de;
	var dataFinal = req.body.ate;

	var de = dateToEN(dataInicial);
	var ate = dateToEN(dataFinal);
	
	console.log(`Recuperando os alertas do estabelecimento do id ${idEstabelecimento}`);
	
	const instrucaoSql = `select distinct a.idAlerta,a.alerta,FORMAT(a.dataAlerta,'dd/MM/yyyy') as dataAlerta,m.codigoMaquina from alerta as a, 
						  kprMaquina as m, kprEstabelecimento as e where a.fkEstabelecimento = ${idEstabelecimento} and
						  dataAlerta between '${de}' and '${ate}' and a.fkMaquinaAlerta = m.idMaquina;`;
	sequelize.query(instrucaoSql, {
		model: Alerta,
		mapToModel: true
	  })
	  .then(resultado => {
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});


module.exports = router;