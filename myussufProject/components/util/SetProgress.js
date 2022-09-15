import { View, Text, StyleSheet, TouchableOpacity } from 'react-native'
import React, { useEffect, useState } from 'react'
import { useContext } from 'react'
import { AuthContext } from '../store/AuthContext'
import { getStudentPerSubject, setProgress } from './http'
import Input from '../Forms/Input'
import { TextInput } from 'react-native-gesture-handler'

const SetProgress = ({navigation}) => {
  const [students, setStudents] = useState([]);
  const [attend, setAttend] = useState([])
  const [rating, setRating]= useState({})
  const authCtx = useContext(AuthContext)


  useEffect(() => {
      async function getStudents(subjectid) {
          const response = await getStudentPerSubject(subjectid);
          setStudents(response.data)
      }
      getStudents(authCtx.subjectid).catch(err => { console.log(err) })


  }, [])

 async function setUnderstanding(){
    for(const key in rating){
      const progress = {
        understanding: rating[key]
      }
      const response = await setProgress(key, authCtx.classid, progress).catch(err=>{console.log(err)})
      console.log(response)
    }
    
  }

  function updateRating(studentid, progress){
    setRating(x=>{
      return {
        ...x,
        [studentid]:progress
      }
    })
    console.log(rating)
  }
  return (
    <View style={styles.container}>


      <View style={styles.tableContainer}>
        <View style={styles.tableRowHeader}>
          <View style={styles.tableColumnHeader}>
            <Text style={styles.textHeader}>Set Progress</Text>
          </View>
        </View>
        <View style={styles.tableRow}>
          
          <View style={styles.tableColumnDate}>
            <Text style={styles.textLineItem}>Student Name</Text>
          </View>
          <View style={styles.tableColumnTotals}>
            <Text style={styles.textLineItem}>Set Progress</Text>
          </View>
        </View>
        {students.map(x => {
          return (
            <View key={x.id} style={styles.tableRow}>
              <View style={styles.tableColumnDate}>
                <Text style={styles.textLineItem}>{x.firstname + " " + x.lastname}</Text>
              </View>
              
              <View style={styles.tableColumnTotals}>
              <TextInput style={styles.input} onChangeText={(y)=>{updateRating(x.id,y)}} />

              </View>
            </View>

          )
        })}
        <TouchableOpacity onPress={() => { setUnderstanding()}} style={{
                backgroundColor: '#608d56',
                borderRadius: 4, padding: 3
            }}>
                <Text style={{ color: "white",textAlign: 'center', fontSize: 18 }}>
                    Submit
                </Text>
            </TouchableOpacity>


      </View>
    </View>
  )
}

export default SetProgress


const styles = StyleSheet.create({
  container: {
    backgroundColor: "#000000",
    flex: 1,
    padding: 10
  },
  tableColumnHeader: {
    alignItems: "center",
    backgroundColor: "#1FE0A2",
    flex: 5,
    justifyContent: "center"
  },
  tableColumnDate: {
    alignItems: "center",
    backgroundColor: "#000000",
    flex: 3,
    justifyContent: "center",
    margin: 1
  },
  tableColumnTotals: {
    alignItems: "center",
    backgroundColor: "#000000",
    flex: 2,
    justifyContent: "center",
    margin: 1
  },
  tableRow: {
    flex: 5,
    flexDirection: "row",
    maxHeight: 30
  },
  tableRowHeader: {
    flex: 5,
    flexDirection: "row",
    maxHeight: 40
  },
  tableContainer: {
    backgroundColor: "#202020",
    borderRadius: 5,
    flex: 1,
    marginTop: 0,
    padding: 10
  },
  input: {
    padding: 3,
    borderRadius: 2,
    fontSize: 14,
    backgroundColor: 'black',
    color: 'white',
    width: 150,
    marginTop: 5},
  textHeader: {
    color: "#000000",
    fontWeight: "bold"
  },
  textHeaderSubTitle: {
    color: "#000000",
    fontSize: 12
  },
  textLineItem: {
    color: "#FFFFFF"
  }
});