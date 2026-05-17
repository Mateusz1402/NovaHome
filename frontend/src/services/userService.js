const API_BASE_URL = "http://localhost:8080/api/v1/user";

export const userService = {
    async login(name, pass){
        const response = await fetch(`${API_BASE_URL}/login`,{
            method: 'PATCH',
            headers: {'Content-type' : 'application/json'},
            body: JSON.stringify({name : name, pass: pass})
        });
        if(!response.ok) throw new Error("Error")
    },

    async logout(){
        const response = await fetch(`${API_BASE_URL}/logout`,{
            method: 'PATCH'});
        if(!response.ok) throw new Error("Error")
    },

    async isLogged(){
        const response = await fetch(`${API_BASE_URL}/is-logged`);
        if(!response.ok) throw new Error("Error")
        return response.json();
    },

    async register(type, name, pass){
        const response = await fetch(`${API_BASE_URL}/register`,{
            method: 'PATCH',
            headers: {'Content-type' : 'application/json'},
            body: JSON.stringify({type: type, name : name, pass: pass})
        
        });
        if(!response.ok) throw new Error("Error")
    }
}