import React, { useState } from 'react'

let names = ["Bala", "Mala", "Sala"]
const List_delete = () => {
  const [namesList, setNames] = useState(false)
  const [selectName, setselectName] = useState([]);

  const deleteName = (i)=>{
      names = names.filter((item,indx) => indx !== i)
      console.log("InDelete:", names);
      
      setNames(namesList ? false: true)

  }
  console.log(names);
  const selectCheck = (e, i) => {
    console.log("select:", i, e.target.value );
    setselectName(e.target.value)
  }
  return (
    

    <div>
       <ul>
        {
            names.map((item,index)=>(
                  <li> <input key={index} value={index} onChange={(index) => {selectCheck(index)}} type='checkbox'/>{item}
                  {console.log(index, selectName); index === selectName && <button onClick={()=>deleteName(index)}>Delete</button>}
                </li>
            ))
        }
       </ul>
       
    </div>
  )
}

export default List_delete;

