import Link from 'next/link'
import styles from './styles.module.scss'

interface LinkTextProps {
    textLink: string
    link: string

}


export function LinkText ({  link, textLink }: LinkTextProps) {
    return (
          <span className={styles.spanContainer}>
              <Link href={link} className='font-bold text-decoration: underline'>
              {textLink}
            </Link>
          </span>



    )
}