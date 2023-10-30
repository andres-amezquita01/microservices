/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
    './src/components/**/*.{js,ts,jsx,tsx,mdx}',
    './src/app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    screens: {
      sm: '640px',
      md: '768px',
      lg: '1024px',
      xl: '1280px',
    },
    extend: {
      backgroundImage: {
        'gradient-radial': "radial-gradient(var(--tw-gradient-stops))",
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
    },
    colors: {
      'dark-blue': '#2957C2',
      'blue': '#0293E6',
      'ligth-blue': '#BDE5FF',
      'white': '#FFFFFF',
      'grey': '#666666',
      'dark-grey': '#DDDEE1',
      'light-grey': '#AAB7BE',
      'green': '#84CA54',
      'red': '#E32323'
    },
    textColor: {
      primary: '#000000',
      secondary: '#FFFFFF',
      white: '#FFFFFF',
      black: '#000000',
      darkblue: '#2957C2',
      lightgrey: '#AAB7BE',
    }
  },
  plugins: [],
};
