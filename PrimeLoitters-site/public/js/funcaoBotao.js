function primeiroQuadro() {
    document.getElementById("quadro2").style.display = "none";
    document.getElementById("quadro3").style.display = "none";

    document.getElementById("quadro1").style.display = "block";
    document.getElementById("quadro1").innerHTML = quadro1();

}

function segundoQuadro() {
    document.getElementById("quadro1").style.display = "none";
    document.getElementById("quadro3").style.display = "none";

    document.getElementById("quadro2").style.display = "block";
    document.getElementById("quadro2").innerHTML = quadro2();
}

function terceiroQuadro() {
    document.getElementById("quadro1").style.display = "none";
    document.getElementById("quadro2").style.display = "none";

    document.getElementById("quadro3").style.display = "block";
    document.getElementById("quadro3").innerHTML = quadro3();
}