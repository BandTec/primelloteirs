var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Leitura = require('../models').Leitura;

/* Recuperar as últimas N leituras */
router.get('/ultimas/:idMaquina', function (req, res, next) {

	let idMaquina = req.params.idMaquina;

	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	console.log(`Recuperando as últimas ${limite_linhas} leituras`);

	const instrucaoSql =   `select top ${limite_linhas}
							dataHora,
							dadosCpu, 
							dadosMemoria,
							dadosDisco, 
							FORMAT(dataHora,'HH:mm:ss') as momento_grafico,
							fkMaquina from kprDado where fkMaquina = ${idMaquina}
							order by dataHora desc`;

	sequelize.query(instrucaoSql, {
		model: Leitura,
		mapToModel: true
	})
		.then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});


// tempo real (último valor de cada leitura)
router.get('/temporeal/:idMaquina', function (req, res, next) {

	let idMaquina = req.params.idMaquina;

	console.log(`Recuperando as últimas leituras`);

	const instrucaoSql = `select top 1 
							dataHora, 
							dadosCPU, 
							dadosMemoria, 
							dadosDisco, 
							fkMaquina from kprDado where fkMaquina = ${idMaquina} 
							order by dataHora desc;`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: Leitura,
		mapToModel: true
	})
		.then(resultado => {
			console.log(resultado[0].dataValues);
			res.json(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});


// estatísticas (max, min, média, mediana, quartis etc)
router.get('/estatisticas', function (req, res, next) {

	console.log(`Recuperando as estatísticas atuais`);

	const instrucaoSql = `select 
							max(temperatura) as temp_maxima, 
							min(temperatura) as temp_minima, 
							avg(temperatura) as temp_media,
							max(umidade) as umidade_maxima, 
							min(umidade) as umidade_minima, 
							avg(umidade) as umidade_media 
						from leitura`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});

});

module.exports = router;
