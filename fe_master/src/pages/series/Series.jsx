import React from 'react'
import Navbar from '../../components/navbar/Navbar'
import List from '../../components/list/List'
import './series.scss'

export default function Series() {
  return (
    <div className='main'>
        <Navbar /> 

        <section>

        <div className='nm-collections-metadata'>
           <div className='nm-collections-metadata-title'><h1>Series</h1></div>
           <div className='nm-collections-metadata-synopsis'>Series move us like nothing else can, whether they’re scary, funny, dramatic, romantic or anywhere in-between. So many titles, so much to experience.</div>
        </div>
        </section>
        <List title='Popular on Netflix'/>
      <List title='International Movies'/>
      <List title='New Releases'/>
      <List title='Blockbuster Movies'/>
    </div>
  )
}
