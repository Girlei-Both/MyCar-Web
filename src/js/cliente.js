// Função para obter a data atual
function obterDataAtual() {
    const data = new Date();
    const opcoes = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return data.toLocaleDateString('pt-BR', opcoes);
}

document.getElementById('dataAtual').innerText = obterDataAtual();

function criarCalendario() {
    const calendarioBody = document.getElementById('calendarioBody');
    calendarioBody.innerHTML = '';

    const dataAtual = new Date();
    const primeiroDia = new Date(dataAtual.getFullYear(), dataAtual.getMonth(), 1);
    const ultimoDia = new Date(dataAtual.getFullYear(), dataAtual.getMonth() + 1, 0);

    const diasNoMes = ultimoDia.getDate();

    let contador = 1;

    // Array de nomes abreviados dos dias da semana
    const diasAbreviados = ['DOM', 'SEG', 'TER', 'QUA', 'QUI', 'SEX', 'SAB'];

    // Criação da linha dos dias da semana
    const linhaDiasSemana = document.createElement('tr');
    diasAbreviados.forEach(diaAbreviado => {
        const celulaDiaSemana = document.createElement('td');
        celulaDiaSemana.textContent = diaAbreviado;
        linhaDiasSemana.appendChild(celulaDiaSemana);
    });
    calendarioBody.appendChild(linhaDiasSemana);

    // Loop para criar as linhas do calendário
    for (let i = 0; i < 6; i++) {
        const linha = document.createElement('tr');

        // Loop para criar os dias da semana
        for (let j = 0; j < 7; j++) {
            const celula = document.createElement('td');

            if (i === 0 && j < primeiroDia.getDay()) {
                // Celulas vazias antes do primeiro dia
                celula.textContent = '';
            } else if (contador > diasNoMes) {
                // Celulas vazias após o último dia
                celula.textContent = '';
            } else {
                celula.textContent = contador;
                contador++;
            }

            linha.appendChild(celula);
        }

        calendarioBody.appendChild(linha);
    }
}

// Chama a função para criar o calendário quando a página carrega
criarCalendario();



function abrirPopup(id) {
    document.getElementById(id).style.display = 'block';
}

function fecharPopup(id) {
    document.getElementById(id).style.display = 'none';
}

function limparFormulario(idForm) {
    // Obtém o formulário pelo ID
    const formulario = document.getElementById(idForm);

    // Percorre todos os elementos do formulário
    for (let elemento of formulario.elements) {
        // Verifica se o elemento é um input ou um select
        if (elemento.tagName === 'INPUT' || elemento.tagName === 'SELECT' || elemento.tagName === 'TEXTAREA') {
            // Limpa o valor do elemento
            elemento.value = '';
        }
    }
}