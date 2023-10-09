import Link from "next/link";

export default function LoginRedirector() {
  return (
    <div className="flex flex-col">
      <p className="font-normal text-m mt-3">Already have an account?</p>
      <Link
        className="bg-white w-full justify-center p-3 text-xl items-center border border-black rounded-full my-5 lg:w-3/4"
        href="/login"
      >
        <p className="text-center w-full">Login</p>
      </Link>
    </div>
  );
}
