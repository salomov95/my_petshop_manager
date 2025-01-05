document.getElementById("filter_date").addEventListener("change", ({ target })=>window.location.assign(`/?filter=${target.value}`))
function openModal() {
  document
    .querySelector(".modal__overlay")
    .classList
    .remove("hidden")
}
function closeModal() {
  document
    .querySelector(".modal__overlay")
    .classList
    .add("hidden")
}
