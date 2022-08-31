import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { useState } from 'react'
import { Avatar, CheckBox } from '@rneui/base';

const Attendance = ({ navigation }) => {
    const [student, setStudents] = useState({ "name": "Mohamed Yussuf" });
    const [selected, setSelected] = useState(false)

    return (
        <><View style={{ alignContent: 'center', alignItems: 'center' }}>
            <Avatar rounded
                source={{
                    uri: "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",
                }} />
            <Text>Ahmed</Text>
        </View>
            <View style={{ flexDirection: 'row' }}>
                <Text style={styles.title}>Mohamed Yussuf </Text>
                <CheckBox center value={selected}/>

            </View></>
    )
}

export default Attendance

const styles = StyleSheet.create({
    title: {
        fontSize: 22,
        padding: 15,
    }
})