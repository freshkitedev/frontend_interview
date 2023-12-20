import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

const countries = [
  {
    name:"India",
    cites:["Chennai", "Bombay"]
  },
  {
    name:"Pak",
    cites:["Karachi", "Lahore"]
  }
]
function App() {
  const [country, setCountry] = useState("");
  const CountrySelect = (e) => {
    console.log(e.target.value, countries, countries[e.target.value].cites);
    setCountry(e.target.value)
    console.log("Country:",country);
  }

  return (
    <div>
      <select onChange={CountrySelect}>
        {
          countries.map((item,index) => (
              <option value={index}>{item.name}</option>
          ))
        }
      </select>
      
       
      {country && <select >
        {
          countries[country].cites.map((item,index) => (
              <option value={index}>{item}</option>
          ))
        }
      </select> }
        
    
    </div>
  );
}

export default App;
