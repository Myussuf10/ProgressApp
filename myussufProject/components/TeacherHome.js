import { Button } from '@rneui/themed'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { Text, View } from 'react-native'
import { Agenda, Calendar, CalendarList } from 'react-native-calendars'
import { Row, Table } from 'react-native-table-component'
import { getClasInfo } from './util/http'

const TeacherHome = ({navigation}) => {
    const [state, setState] = useState([]);

    const x =()=>{

      console.log(state);
    }

    useEffect(()=>{
      async function getClas(x){
       const classess = await getClasInfo(x);
        setState(classess)
      }
      getClas(2);
  
    }, [])
    const p = state.map((x)=>{
      <Table>
        <Row>${x}</Row>
      </Table>
    })
    
    console.log(p)
  return (
    <View>
        <Button onPress={()=>{x(2)}}/>
      {p}
    </View>


  )
}

export default TeacherHome