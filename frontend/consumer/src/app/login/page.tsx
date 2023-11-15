'use client'
import { Form } from "@/components/Form"
import LogoLogin from "./LogoLogin";
import { useRouter } from 'next/navigation';

function LoginPage() {

  const router = useRouter()
  const loginSubmit = async (formData:any) => {
    try {
      const response = await fetch('http://35.227.37.171:8090/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          usernameOrEmail: formData.email,
          password: formData.Password,
        }),
      });

      if (!response.ok) {
        alert(response)
      }

      // Manejar la respuesta exitosa según tus necesidades
      // Puedes redirigir al usuario a otra página, por ejemplo:
      router.push('/home');
    } catch (error) {
      console.log(error)
    }
  };

  return (
   <>
   <LogoLogin width={1700} height={700} />
   <Form
    onSubmit={loginSubmit}
    title="Login"
    description="Don’t have an account?"
   >
       <div className='justify-content-center '>
           <Form.LinkText
           link="/login/forget-password"
           textLink="Create a new one"
            />
       </div>
    <div className='my-[10px] flex flex-col gap-4'>
      <Form.Input
        name="email"
        label="Email"
        placeholder="youremail@company.com"
      />
      <Form.Input
            placeholder='Choose a password'
            label='Password'
            name='Password'
            type='password'
          />
    </div>
       <Form.Footer
           description=" "
           link="/login/forget-password"
           textLink="Forgot password?"
       />
    <div>

    </div>
    <Form.SubmitButton
    buttonText="Continue"
    //isLoading={isLoading}
    />
   </Form>
   </>
  )
}

export default LoginPage