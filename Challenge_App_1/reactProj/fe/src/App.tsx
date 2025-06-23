import { Checkbox } from "@/components/ui/checkbox"
import './index.css'
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { useEffect, useState } from "react"
import axios from "axios"



function App() {
  // ui updation 
  const [Challenge,setChallenge] = useState([]);
  

 useEffect(()=>{
  const fetchChallenges = async () =>{
   const getResponse = await axios.get('http://localhost:8080/getchallenges')

    setChallenge(getResponse.data);
  };
  
  fetchChallenges();
 },[])




  return (
   <>
   
 
  <h1 className="scroll-m-20 mb-20 text-center text-4xl font-extrabold tracking-tight text-balance"
  style={{}}
  >
       Challenges 
    </h1>
    
 
<form className="max-w-sm mx-auto">
      <div className="mb-5">
        <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
          Enter Challenge
        </label>
        <input
          type="text"
          id="email"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Work hard"
          required
        />
      </div>
      <div className="mb-5">
        <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
          Status
        </label>
        <input
          type="text"
          id="password"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 "
          placeholder='Done or Not done'
          required
        />
      </div>
      <div className="flex items-start mb-5">
       
       
      </div>
      <button 
        type="submit"
        className="text-white   bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
      >
        Submit
      </button>
    </form>
    <div className='mb-10'></div>









 <h1 className="scroll-m-20 text-center text-4xl font-extrabold tracking-tight text-balance  "
  style={{}}
  >
       Your Challenges 
    </h1>
 
<Table>
  <TableHeader>
    <TableRow>
      <TableHead>Description</TableHead>
      <TableHead className="text-center">Not Done</TableHead>
      <TableHead className="text-center">Done</TableHead>
      <TableHead className="text-center">Delete</TableHead>
    </TableRow>
  </TableHeader>
  <TableBody>
    {Challenge.map((item: any) => (
      <TableRow key={item.id}>
        <TableCell>{item.description}</TableCell>
        <TableCell className="text-center">
          {!item.status && <span>✔️</span>}
        </TableCell>
        <TableCell className="text-center">
          {item.status && <span>✔️</span>}
        </TableCell>
        <TableCell className="text-center">
          <Checkbox id={`delete-${item.id}`} />
        </TableCell>
      </TableRow>
    ))}
  </TableBody>
</Table>
<div className='mb-20'></div>
 

   </>
  )
}

export default App