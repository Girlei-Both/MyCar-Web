let clienteImg = document.getElementById('clienteImg');
let empreendedorImg = document.getElementById('empreendedorImg');

function atualizarValorInput(valor) {

    // Lógica para atualizar o valor conforme necessário
    document.getElementById('tipoInput').value = valor;

    // Define a opacidade da imagem clicada para 0.5 (opaca)
    if (valor === 1) {
        clienteImg.style.opacity = 1;
        empreendedorImg.style.opacity = 0.5;
    } else if (valor === 2) {
        empreendedorImg.style.opacity = 1;
        clienteImg.style.opacity = 0.5;
    }
}

//LÓGICA PARA LIMPAR OS INPUTS
function limparInputs() {

    var inputs = document.querySelectorAll('input');
    inputs.forEach(function (input) {
        input.value = "";
    });

    var select = document.querySelector('select');
    select.selectedIndex = 0;
}

//LÓGICA PARA MOSTRAR POPUPS
function mostrarPopup() {
    document.getElementById('popupAjuda').style.display = 'block';
    document.getElementById('fundo').style.opacity = "10%";
}

function fecharPopup() {
    document.getElementById('popupAjuda').style.display = 'none';
    document.getElementById('fundo').style.opacity = "100%";
}

//CONTROLE DESLIZANTE
document.getElementById('range').addEventListener('input', function () {
    document.getElementById('valorInput').textContent = this.value;
});