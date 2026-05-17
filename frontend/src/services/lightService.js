const API_BASE_URL = 'http://localhost:8080/api/v1/lights';

export const lightService = {
async getAll(){
  const response = await fetch(`${API_BASE_URL}`);
  if(!response.ok) throw new Error("Error");
  return response.json();
},

async isRunning(id){
  const response = await fetch(`${API_BASE_URL}/${id}/is-running`);
  if(!response.ok) throw new Error("Error");
  return response.json();
},

async isConnected(id){
  const response = await fetch(`${API_BASE_URL}/${id}/is-connected`);
  if(!response.ok) throw new Error("Error");
  return response.json();
},

async updateBrightness(id, level){
  const response = await fetch(`${API_BASE_URL}/${id}/brightness`, {
    method: 'PATCH',
    headers: {'Content-type': 'application/json'},
    body: JSON.stringify({level: Number(level)})
  });
  
  if(!response.ok) throw new Error("Error");
},


async turnOn(id){
  const response = await fetch(`${API_BASE_URL}/${id}/turn-on`,{
    method: 'POST'});

  if(!response.ok) throw new Error("Error");
},

async turnOff(id){
  const response = await fetch(`${API_BASE_URL}/${id}/turn-off`, {
    method: 'POST'});

  if(!response.ok) throw new Error("Error");
},

async connect(id){
  const response = await fetch(`${API_BASE_URL}/${id}/connect`, {
    method: 'PATCH'});
  if(!response.ok) throw new Error(errorMessage);
},

async disconnect(id){
  const response = await fetch(`${API_BASE_URL}/${id}/disconnect`,{
    method: 'PATCH'});
  if(!response.ok) throw new Error("Error"); 
}
}
