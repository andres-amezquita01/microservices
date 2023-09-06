import Link from 'next/link'
import styles from './styles.module.scss'

interface FooterProps {
  description: string
  textLink: string
  link: string

}


export function Footer ({ description, link, textLink }: FooterProps) {
  return (
    <div className='flex justify mt-3'>
        <div className="flex items-center text-[12px]" >
            <input
                type="checkbox"
                name="rememberMe"
                defaultChecked
            />
            <label className="ml-2">Remember me</label>
        </div>
        <div className= {styles.div}>
          <span className='text-[12px]'>
            {description}{' '}
              <Link href={link} className='font-bold text-decoration: underline'>
              {textLink}
            </Link>
          </span>
        </div>
    </div>


  )
}