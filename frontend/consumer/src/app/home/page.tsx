'use client'

import { useSession, signOut} from "next-auth/react";
import { redirect } from "next/navigation";

function HomePage(){
  const { data: session, status } = useSession();
  const close = () => {
    signOut({redirect:true, callbackUrl: "/"})
  }

  return (
    <>
      <div> Hola {session?.user?.name || "No user"}</div>
      <button onClick={close}>Close session</button>
    </>
  )
}

export default HomePage;
