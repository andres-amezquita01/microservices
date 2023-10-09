import Link from "next/link";

export default function SignUpWithEmailButton() {
  return (
    <Link
      className="bg-dark-blue text-white rounded-full p-4 mt-5 w-full text-xl lg:w-3/4"
      href="/register"
    >
      <p className="text-center w-full">Sign up with your email</p>
    </Link>
  );
}
