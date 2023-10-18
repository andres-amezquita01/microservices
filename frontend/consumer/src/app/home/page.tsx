'use client'

import Navbar from "../../components/NavBar/NavBar";
import Principal from "./components/principal";

function HomePage(){
 
  return (
    <>
      <div><Navbar /></div>
      <div className="bg-white m-2">
        <div><Principal /></div>
      </div>
    </>
  )
}

export default HomePage;
