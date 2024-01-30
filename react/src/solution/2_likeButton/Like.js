import{HeartIcon,SpinnerIcon} from "./icon"
import "./Like.css"
import { useState } from "react";

function App() {
  const [liked,setLiked] = useState(false);
  const [isFetching,setIsFetching] = useState(false);
  const[error,setError] = useState(null);

  const handleLikeUnlike = async() =>{
    setIsFetching(true)
    setError(null)
    try {
      const response = await fetch(
        "https://www.greatfrontend.com/api/questions/like-button",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            //liked => true(unlike btn to like) flase(unlike btn to liked)
            action: liked ? "unlike" : "like",
          }),
        },
      );

      if (response.status >= 200 && response.status < 300) {
        //if(liked is true then is flase or when it is flase then it is true)
        setLiked(!liked);
      } else {
        const res = await response.json();
        setError(res.message);
        return;
      }
    //Code that should always be executed
    } finally {
      setIsFetching(false);
    }
  }

return (
    <div className="btn">
        <button className={liked ? "likedbtn":"likebtn"}
          onClick={handleLikeUnlike}> 
            {isFetching ? <SpinnerIcon /> : <HeartIcon />}
            {liked ?"Liked":"Like"}
        </button>
        {error && <div>{error}</div>}
     </div>
  );
}
export default App;
