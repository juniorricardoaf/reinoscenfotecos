export const setItem=(llave,valor)=>{
    localStorage.setItem(llave, JSON.stringify(valor));
}
export const getItem=(llave)=>{
    return JSON.parse(localStorage.getItem(llave));
}