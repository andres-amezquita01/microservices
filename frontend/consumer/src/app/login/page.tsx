'use client'
import { Form } from "@/components/Form"

function LoginPage() {

  const loginSubmit = async (formData: any) => {
    /*startLoading()
    await authRouter({
      endpoint: "login",
      formData,
      redirectRoute: "/dashboard",
    });
    finishLoading()
*/
  }

  return (
   <>
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