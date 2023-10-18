'use client'

import { useSession, signOut} from "next-auth/react";
import { redirect } from "next/navigation";
import Navbar from "../../components/NavBar/NavBar";
import Principal from "./components/principal";

function HomePage(){
  const { data: session } = useSession();
  const close = () => {
    signOut({redirect:true, callbackUrl: "/"})
  }

  return (
    <>
      <div><Navbar /></div>
      <div className="bg-white m-2">
        <div><Principal /></div>
        <button onClick={close} className="m-2">Close session</button>

      </div>
    </>
  )
}

export default HomePage;
