const API_BASE_URL = 'http://localhost:8080/api/v1/thermostats';

export const thermostatService = {
    async getAll(){
        const response = await fetch(`${API_BASE_URL}`);
        if(!response.ok) throw new Error("Error");
        return response.json();
    },

    async turnOn(id){
        const response = await fetch(`${API_BASE_URL}/${id}/turn-on`, {
            method: 'POST'});
        if(!response.ok) throw new Error("Error");
    },

    async turnOff(id){
        const response = await fetch(`${API_BASE_URL}/${id}/turn-off`, {
            method: 'POST'});
        if(!response.ok) throw new Error("Error");
    },

    async isRunning(id){
        const response = await fetch(`${API_BASE_URL}/${id}/is-running`);
        if(!response.ok) throw new Error("Error");
        return response.json();
    },

    async connect(id){
        const response = await fetch(`${API_BASE_URL}/${id}/connect`, {
            method: 'PATCH'});
        if(!response.ok) throw new Error("Error");
    },

    async disconnect(id){
        const response = await fetch(`${API_BASE_URL}/${id}/disconnect`,{
            method: 'PATCH'});
        if(!response.ok) throw new Error("Error");
    },

    async isConnect(id){
        const response = await fetch(`${API_BASE_URL}/${id}/is-connect`);
        if(!response.ok) throw new Error("Error");
        return response.json();
    },

    async setTemperature(id, temp){
        const response = await fetch(`${API_BASE_URL}/${id}/set-temp`,{
            method: 'PATCH',
            headers: {'Content-type' : 'application/json'},
            body: JSON.stringify(Number(temp))
        });
        if(!response.ok) throw new Error("Error");
        return response;
    },

    async getActualTemp(id){
        const response = await fetch(`${API_BASE_URL}/${id}/get-temp`);
        if(!response.ok) throw new Error("Error");
        return response.json();
    },

    async getSettedTemp(id){
        const response = await fetch(`${API_BASE_URL}/${id}/get-setted-temp`);
        if(!response.ok) throw new Error("Error");
        return response.json();
    }

}