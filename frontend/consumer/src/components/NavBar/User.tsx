"use client";
import { IoIosArrowDown } from "react-icons/io";
import { BiSolidUser } from "react-icons/bi";
import { useSession, signOut } from "next-auth/react";
import { redirect } from "next/navigation";
import { Menu, Transition } from "@headlessui/react";
import { Fragment, useEffect, useState } from "react";


export function User() {

  const { data: session } = useSession();
  const [name, setName] = useState(null);
  const iconSize = "35px";

  const close = () => {
    signOut({redirect:true, callbackUrl: "/"})
  }

  useEffect(() => {
    const fetchSessionInfo = async () => {
      try {
        const response = await fetch('http://35.227.37.171:8090/session-info', {
          method: 'GET'
        });

        if (!response.ok) {
          throw new Error('Failed to fetch session info');
        }
        const data = await response.json();
        const userName = data.name; // Extraer el nombre del usuario de la respuesta
        setName(userName);
  
        // Agrega un console.log para ver el nombre obtenido
        console.log('Nombre del usuario obtenido:', userName);
      } catch (error) {
        console.error('Error fetching session info:', error);
      }
    };
  
    if (session) {
      // Agrega un console.log para verificar que haya una sesión
      console.log('Sesión disponible. Iniciando solicitud a la API...');
      fetchSessionInfo();
    }
  }, [session]);
    
  return (
    <Menu as="div" className="relative inline-block text-left">
      <div>
        <Menu.Button className="flex w-full justify-center  rounded-md bg-white px-3  text-sm font-semibold  shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
          <div className="p-1 border-4 border-solid border-dark-blue rounded-full m-1">
            <BiSolidUser color="darkblue" style={{ fontSize: iconSize }} />
          </div>
          <div>
            <div className="mt-2 font-semi-bold text-darkblue justify-content-center " >
              {name || session?.user?.name || "No user"}
              <p className="text-black">Admin</p>
            </div>
          </div>
          <IoIosArrowDown
              className="mt-4 mr-1 h-5 w-5 text-gray-800"
              aria-hidden="true"
            />
        </Menu.Button>
      </div>

      <Transition
        as={Fragment}
        enter="transition ease-out duration-100"
        enterFrom="transform opacity-0 scale-95"
        enterTo="transform opacity-100 scale-100"
        leave="transition ease-in duration-75"
        leaveFrom="transform opacity-100 scale-100"
        leaveTo="transform opacity-0 scale-95"
      >
        <Menu.Items className="absolute right-0 z-10 mt-2 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
          <div className="py-1">
              <Menu.Item>
                {({ active }) => (
                    <button onClick={close} className="m-2 ">
                      Close session</button>
                )}
              </Menu.Item>
          </div>
        </Menu.Items>
      </Transition>
    </Menu>
  );
}
