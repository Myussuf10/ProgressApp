import { View, Text, KeyboardAvoidingView, TouchableOpacity, StyleSheet } from 'react-native'
import React from 'react'
import { Avatar } from '@rneui/base'
import { useState } from 'react'
import { useContext } from 'react'
import { AuthContext } from './store/AuthContext'
import { useEffect } from 'react'
import SubjectPage from './SubjectPage'

const ParentsHome = ({navigation}) => {
  const [children, setChildren] = useState([]);
  const authCtx = useContext(AuthContext)

  useEffect(()=>{
    
    // children.push(authCtx.userInfo.children.firstname)
    console.log(authCtx)
    const children = []
    for(const x in authCtx.userInfo.children){
      const child = {
        id : authCtx.userInfo.children[x].id,
        name: authCtx.userInfo.children[x].firstname + " " + authCtx.userInfo.children[x].lastname,
        subjects: authCtx.userInfo.children[x].subjects,
      }
      children.push(child);
    }
    setChildren(children)
    console.log(authCtx.userInfo)
    
  }, [authCtx])

  function navPage(childid,subjectid){
    authCtx.setChild(childid,subjectid)
    navigation.navigate("Subject")
  }

  return (

    <KeyboardAvoidingView behavior='height'
    style={{ backgroundColor: '#B4B4B4', flex: 1 }}>
    <View style={{ alignContent: "center", flexDirection: 'row', justifyContent: "center", marginTop: 5, padding: 5 }}>
      <Avatar rounded
        source={{
          uri:
            "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",
        }} />
      <Text style={{ fontSize: 18, padding: 5 }}>{"Mr" + " " + authCtx.userInfo.lastname }</Text>
    </View>
    <Text style={styles.title}>Children</Text>
    <View style={styles.items}>
    </View>
    {children.map(child => {
                return (
                    <View key={child.id} style={styles.items}>
                        <Text style={styles.text}>{child.name}</Text>
                        {child.subjects.map((subject=>{
                          return(
                            <TouchableOpacity key={subject.id} onPress={()=>navPage(child.id, subject.id)} style={{
                            backgroundColor: '#608d56',
                            borderRadius: 2, padding: 4, width: 80, margin: 2
                        }}>
                            <Text style={{ textAlign: 'center' }}>{subject.subjectname}</Text>
                        </TouchableOpacity>
                          )
                        }))}
                        
                        
                    </View>
                )

            })}


  </KeyboardAvoidingView>


  )
}

export default ParentsHome


const styles = StyleSheet.create({

  txt: {
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center"
  }, 
  items: {
    flexDirection: "row",
    
},

  background: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    padding: 7,
    flex: 1
},
  title: {
    fontSize: 22,
    textAlign: 'left',
    margin: 8
  }, button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    width: 220
  },


});