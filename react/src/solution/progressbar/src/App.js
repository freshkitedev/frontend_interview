import "./styles.css";
import ProgressBar from "./Progress Bar.js"; 
import { useEffect, useState } from "react";

export default function App() {
  const [value, setValue] = useState(0);
  const [success, setSuccess] = useState(false);
  const maxValue = 100; 

  useEffect(() => {
    const intervalId = setInterval(() => {
      setValue((val) => {
        const nextValue = val + 1;
        if (nextValue > maxValue) {
          clearInterval(intervalId); 
          setSuccess(true); 
          return maxValue;
        }
        return nextValue;
      });
    }, 100);
  }, []); 
  
  return (
    <div className="app">
      <span>Progress Bar</span>
      <ProgressBar value={value} onComplete={() => setSuccess(true)} />
      <span>{success ? "completed" : "Loading..."}</span>
    </div>
  );
}
