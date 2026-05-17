import React , {useState, useEffect, useCallback} from 'react';
import {Lightbulb, LightbulbOff} from 'lucide-react';
import {lightService} from './services/lightService';
import {thermostatService} from './services/thermostatService';
import './App.css'; 



const LightCard = ({id, brightness, onUpdate, setGlobalError}) => {
  const [isRunning, setIsRunning] = useState(false);
  const [isConnected, setIsConnected] = useState(false);

  const checkStatus = useCallback(async () => {
    const status = await lightService.isRunning(id);
    setIsRunning(status);

    const connection = await lightService.isConnected(id);
    setIsConnected(connection);
  }, [id]);

  useEffect(() => {
    checkStatus();
    const interval = setInterval(checkStatus, 3000);
    return () => clearInterval(interval);
  }, [checkStatus]);

  const handleBrightnessChange = async (e) => {
    const value = e.target.value;
    await lightService.updateBrightness(id, value);
    onUpdate();
  };

  const handleAction = async (action) => {
    try{
      await lightService[action](id);
      onUpdate();
      setGlobalError(null);
    } catch (err){
      setGlobalError(err.message);
      setTimeout(() => setGlobalError(null, 5000));
    }
  };

  return (
    <div className="card">
      <div className="status-container">
        <div className={`status-dot ${isRunning ? 'running' : 'stopped'}`} />
        <div className="label">
          <span className="status-label">{isRunning ? 'Running' : 'Stopped'}</span>
        </div>
        <div className="temp"></div>

        <div className={`status-dot ${isConnected ? 'connected' : 'disconnected'}`} />
        <div className="label">
          <span className="status-label">{isConnected ? 'Connected' : 'Disconnected'}</span>
        </div>
        <div className="temp"></div>
      </div>
      <div className="icon-row">
        {brightness > 0 ? <Lightbulb className="icon-active" size={48}/>
          : <LightbulbOff className="icon-inactive" size={48}/>}
        <h2>Light {id+1}</h2>        
      </div>
      <div className="control-group">
        <label>Brightness: <strong>{brightness}%</strong></label>
        <input 
          type="range"
          min="0"
          max="100"
          value={brightness}
          onChange={handleBrightnessChange}
          className="slider"
        />
      </div>
      <div className="button-group">
        <button className="btn-primary" 
                onClick={() => handleAction(isRunning ? 'turnOff' : 'turnOn')}>
                  {isRunning ? 'Off' : 'On'}
        </button>
        <button className={`btn-secondary ${!isRunning ? 'disable-btn' : ''}`} 
                onClick={() => handleAction(isConnected ? 'disconnect' : 'connect')}>
                {isConnected ? 'Disconnect' : 'Connect'}
        </button>
      </div>
    </div>
  );
};

const ThermostatCard = ({id, temperature, settedTemp, ouUpdate, setGlobalError}) => {
  const [isRunning, setIsRunning] = useState(false);
  const [isConnect, setIsConnect] = useState(false);
  const [temp, setTemp] = useState(temperature);
  const [targetTemp, setTargetTemp] = useState(settedTemp)

  const checkStatus = useCallback(async () => {
    const status = await thermostatService.isRunning(id);
    setIsRunning(status);

    const connection = await thermostatService.isConnect(id);
    setIsConnect(connection);

    const actual = await thermostatService.getActualTemp(id);
    setTemp(actual);

    const settedTemp = await thermostatService.getSettedTemp(id);
    setTargetTemp(settedTemp);
  }, [id]);

  useEffect(() => {
    checkStatus();
    const interval = setInterval(checkStatus, 3000);
    return () => clearInterval(interval);
  }, [checkStatus]);

  const handleTempChange = async(e) => {
    const value = e.target.value;
    setTargetTemp(value);
    try{
      await thermostatService.setTemperature(id, value);
      ouUpdate();
    } catch (err){
      setGlobalError(`Failed to set temperature to ${value}°C`);
    }
    
  };

  const handleAction = async (action) => {
    try{
      await thermostatService[action](id);
      ouUpdate();
    }catch (err){
      setGlobalError(`Failed to perform ${action}`);
    }
    
    
  };
  return(
    <div className="card">
      <div className="status-container">
        <div className={`status-dot ${isRunning ? 'running' : 'stopped'}`}></div>
        <div className="label">
          <span className="status-label">{isRunning ? 'Running' : 'Stopped'}</span>
        </div>
        <div className='temp'></div>

        <div className={`status-dot ${isConnect ? 'connected' : 'disconnected'}`}></div>
        <div className="label">
          <span className='status-label'>{isConnect ? 'Connected' : 'Disconnected'}</span>
        </div>
        <div className='temp'></div>
      </div>
      <div className='main'>
        <h3>Actual temperature: {Number(temp).toFixed(1)}°C</h3>
        <h2> Thermostat {id+1}</h2>
      </div>
      <div className='control-group'>
        <label>Setted temperature: <strong>{Number(targetTemp).toFixed(1)}°C</strong></label>
        <input 
          type='range' 
          min="10" 
          max="30"
          value={targetTemp}
          onChange={handleTempChange}
          className='slider'>
        </input>
      </div>
      <div className='button-group'>
        <button className='btn-primary' onClick={() => handleAction(isRunning ? 'turnOff' : 'turnOn')}>{isRunning ? 'Off' : 'On'}</button>
        <button className={`btn-secondary ${!isRunning ? 'disable-btn' : ''}`} 
        onClick={() => handleAction(isConnect ? 'disconnect' : 'connect')}>{isConnect ? 'Disconnect' : 'Connect'}</button>
      </div>
    </div>
  );
};



function App(){
  const [lights, setLights] = useState([]);
  const [thermostats, setThermostats] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  const loadData = useCallback(async () => {
    try{
      const data = await lightService.getAll();
      const thermoData = await thermostatService.getAll();
      setLights(data);
      setThermostats(thermoData);
      setError(null);
    } catch (err){
      setError("Error");
    } finally{
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    loadData();
  }, [loadData]);

  if (isLoading) return <div className="loader">Loading...</div>;

  return (
    <div className="app-container">
      <header className="header">
        <h1>NovaHome</h1>
      </header>

      {error && <div className="error-banner">{error}</div>}

      <main className="grid">
        {lights.map((brightness, index) => (
          <LightCard 
            key={index}
            id={index}
            brightness={brightness}
            onUpdate={loadData}
            setGlobalError={setError}
          />
        ))}
        
        {thermostats.map((temp, index) => (
          <ThermostatCard
            key={index}
            id={index}
            temperature={temp}
            settedTemp={temp}
            ouUpdate={loadData}
            setGlobalError={setError}
          />
        ))}
        
      </main>
    </div>
  );
}
export default App;
