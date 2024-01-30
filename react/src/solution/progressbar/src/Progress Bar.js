import { useEffect, useState } from "react";
import {MAX , MIN} from "./constants.js";
const ProgressBar = ({ value = 0, onComplete = () => {} }) => {
    const [percent, setPercent] = useState(value);
    useEffect(() => {
        setPercent(Math.min(MAX, Math.max(value, MIN)));
        if (value >= MAX) {
            onComplete();
            }
    }, [value]); 
    return (
        <div className="progress">
            <span style={{ color: percent > 69 ? "white" : "black" }} >{percent} %</span>
            <div style={{
                background: `linear-gradient(to right,#00c251 ${percent}%, transparent ${percent}%)`,
                transform: `scaleX(${percent / MAX})`,
                transformOrigin: "left"
              }}
              role="progressbar"
              aria-valuemin={MIN}
              aria-valuemax={MAX}
            />
  

        </div>
    );
};
export default ProgressBar;