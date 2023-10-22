"use client";
import { IoIosArrowDown } from "react-icons/io";
import { BiSolidUser } from "react-icons/bi";
import { useSession, signOut } from "next-auth/react";
import { redirect } from "next/navigation";
import { Menu, Transition } from "@headlessui/react";
import { Fragment } from "react";



function classNames(...classes: any[]) {
  return classes.filter(Boolean).join(" ");
}

export function User() {
  const { data: session } = useSession();
  const iconSize = "35px"; 
  
  const close = () => {
    signOut({redirect:true, callbackUrl: "/"})
  }
  
/*
  return (
    <div className="flex items-center mr-4">
      <div className="p-1 border-4 border-solid border-dark-blue rounded-full m-1">
        <BiSolidUser color="darkblue" style={{ fontSize: iconSize }} />
      </div>
      <div className="font-semi-bold text-darkblue" >
          {session?.user?.name || "No user"}
          <p className="text-black">Admin</p>
      </div>
      
    </div>
  );
  
}
*/


  return (
    <Menu as="div" className="relative inline-block text-left">
      <div>
        <Menu.Button className="flex w-full justify-center  rounded-md bg-white px-3  text-sm font-semibold  shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
          <div className="p-1 border-4 border-solid border-dark-blue rounded-full m-1">
            <BiSolidUser color="darkblue" style={{ fontSize: iconSize }} />
          </div>
          <div>
            <div className="mt-2 font-semi-bold text-darkblue justify-content-center " >
              {session?.user?.name || "No user"}
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
