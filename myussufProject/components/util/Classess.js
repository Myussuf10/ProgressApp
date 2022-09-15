import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import { useContext } from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { getClassPerSubject } from './http'
import { AuthContext } from '../store/AuthContext'
import { TouchableOpacity } from 'react-native'
import SubjectTeacher from './SubjectTeacher'
import { Avatar } from '@rneui/base'
import SubjectPage from '../SubjectPage'

const Classess = ({ navigation }) => {
    const [classes, setClasses] = useState([])
    const authCtx = useContext(AuthContext)

    useEffect(() => {
        async function getClasses(subjectid) {
            const response = await getClassPerSubject(subjectid)
            const AllClasses = []
            for (const i in response) {
                const oneClass = {
                    "id": response[i].id,
                    "date": response[i].dow,
                    "time": response[i].time,
                    "topic": response[i].topic
                }
                AllClasses.push(oneClass)
            }
            setClasses(AllClasses)
            console.log(classes)
        }
        getClasses(authCtx.subjectid)

    }, [])

    function goClasses(classid){
        authCtx.setClass(classid);
        navigation.navigate("SubjectTeacher")
    }


    return (
        <View style={{ backgroundColor: '#B4B4B4', flex: 1 }}>
            <View style={{ alignContent: "center", flexDirection: 'row', justifyContent: "center", marginTop: 5, padding: 5 }}>
                <Avatar rounded
                    source={{
                        uri:
                            "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",
                    }} />
                <Text style={{ fontSize: 18, padding: 5 }}>{"Mr" + " " + authCtx.userInfo.lastname}</Text>
            </View>
            <Text style={styles.title}>Class Dates and Topics</Text>
            <View style={styles.items}>

                <TouchableOpacity key={classes.id} style={{
                    backgroundColor: "#fbd692", borderRadius: 5,
                    padding: 10,
                    width: 200,
                    margin: 5
                }} onPress={() => { navigation.navigate("SubjectPage") }}>
                    <Text style={styles.txt}>Timetable</Text>
                </TouchableOpacity>
            </View>
            <View style={styles.items}>
                {classes.map(classes => {
                    return (
                        <TouchableOpacity key={classes.id} style={styles.button} onPress={() => { goClasses(classes.id) }}>
                            <Text style={styles.txt}>{classes.topic}</Text>
                        </TouchableOpacity>
                    )
                })}
            </View>
        </View>
    )
}

export default Classess

const styles = StyleSheet.create({
    button: {
        backgroundColor: '#608d56',
        borderRadius: 5,
        padding: 10,
        width: 200,
        margin: 5
    },
    title: {
        fontSize: 20,
        textAlign: 'center',
        margin: 8,
        paddingTop: 10
    },
    items: {
        alignContent: "center",
        justifyContent: "center",
        marginLeft: 105,
        marginTop: 10
    },
    txt: {
        fontSize: 15,
        alignItems: "center",
        justifyContent: "center",
        textAlign: "center",
    }
})

