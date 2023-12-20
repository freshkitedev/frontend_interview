// Display below country names in dropdown and when a country name 

import { useState } from "react";

// is selected display continent and cites in another dropdown 
const countries = [
    {
      name:"India",
      continent:"Asia",
      cites:["Chennai", "Bombay"]
    },
    {
      name:"US",
      continent:"South America",
      cites:["San Jose", "Santa Clara"]
    }
  ]

  export function Dropdown() {
    const [country, setCountry] = useState("0")
    
    const CountrySelect = (e) => {
    console.log(e.target.name);
    console.log(e.target.value, countries, countries[e.target.value].cites);
    setCountry(e.target.value)
    console.log("Country:",country);
  }

  return (
    <div>
      <select onChange={CountrySelect}>
        {countries.map((item, index) => (
          <option key={index} name={index + 2} value={index}>
            {item.name}
          </option>
        ))}
      </select>

      {country && (
        <>
          <h3>{countries[country].continent}</h3>
          <select>
            {countries[country].cites.map((item, index) => (
              <option key={index} value={index}>
                {item}
              </option>
            ))}
          </select>
        </>
      )}
    </div>
  );
}